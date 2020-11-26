package main;

import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.List;

public class ActorList {
    private ArrayList<Actor> list = new ArrayList<>();
    public ActorList() {}



    public void getInputActors(List<ActorInputData> a) {
        if (a.size() != 0) {
            for (int i = 0; i < a.size(); i++) {
                Actor person = new Actor(a.get(i).getName(), a.get(i).getCareerDescription(),
                        a.get(i).getFilmography(), a.get(i).getAwards());
                list.add(person);
            }
        }
    }

    @Override
    public String toString() {
        return "ActorList{" +
                "list=" + list +
                '}';
    }

    public ArrayList<Actor> getList() {
        return list;
    }

    public void setList(ArrayList<Actor> list) {
        this.list = list;
    }

}
