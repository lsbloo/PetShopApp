package com.example.osvaldoairon.petshopapp.Rep;
import com.example.osvaldoairon.petshopapp.Rep.modelo.Animal;
/**
 * Created by osvaldoairon on 12/01/2018.
 */

public interface DaoPet  {


    public long insert(Animal animal);

    public int deletarAnimal();

    public int countArray();
}
