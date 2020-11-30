package auxiliarylists;

import auxiliaryentities.Actor;
import fileio.ActorInputData;

import java.util.ArrayList;
import java.util.List;

public class ActorList {
    private ArrayList<Actor> list = new ArrayList<>();
    public ActorList() {
    }


    /**
     * Making a copy of the actors given in the input to assure that we do not mess the input up
     * while also initialising the list for the actors
     * @param a Actors given in input
     */
    public void getInputActors(final List<ActorInputData> a) {
        if (a.size() != 0) {
            for (int i = 0; i < a.size(); i++) {
                Actor person = new Actor(a.get(i).getName(), a.get(i).getCareerDescription(),
                        a.get(i).getFilmography(), a.get(i).getAwards());
                list.add(person);
            }
        }
    }

    /**
     * Method toString to ensure prints
     * @return A string with the elements of the entity
     */
    @Override
    public String toString() {
        return "ActorList{"
                + "list=" + list
                + '}';
    }

    /**
     * Getter for the actorList
     * @return The list of actors
     */
    public ArrayList<Actor> getList() {
        return list;
    }

    /**
     * Setter for the actorList
     * @param list An actorList
     */
    public void setList(final ArrayList<Actor> list) {
        this.list = list;
    }

}
