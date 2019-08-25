package com.test.myfirst.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FirstFragment extends Fragment {
    PokemonRecycleAdapter adapter;
    ArrayList pokeArray;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<PokemonPojo> call = service.getPokemonObj();

        call.enqueue(new Callback<PokemonPojo>() {
            @Override
            public void onResponse(Call<PokemonPojo> call, Response<PokemonPojo> response) {
                PokemonPojo pokemonPojo = response.body();
                pokeArray = new ArrayList<>(pokemonPojo.getPokemon());
                generateData(pokeArray);
            }

            @Override
            public void onFailure(Call<PokemonPojo> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "Try it later..!", Toast.LENGTH_LONG).show();
            }
        });
        return inflater.inflate(R.layout.fragment_first, container, false);
    }
    public void generateData(ArrayList<Pokemon> pokemonsList){

        /*   ArrayList<Pokemon> pokes=(ArrayList<Pokemon>) pokemonsList;*/
        adapter=new PokemonRecycleAdapter(pokemonsList,getActivity().getApplicationContext());
        @SuppressLint("WrongConstant") LinearLayoutManager linearLayout=new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView=getView().findViewById(R.id.recycle_poke);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapter);


    }
}
