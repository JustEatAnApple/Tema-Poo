package auxiliarylists;

import auxiliaryentities.Action;
import fileio.ActionInputData;

import java.util.ArrayList;
import java.util.List;

public class ActionList {
    private ArrayList<Action> list = new ArrayList<Action>();

    /**
     * @return The number of commands that will be given
     */
    public int getSize() {
        return list.size();
    }

    /**
     * @param i The index of the action that we want to grab
     * @return The action at the index i
     */
    public Action getAction(final int i) {
        return list.get(i);
    }

    /**
     * Making a copy of the actions given in the input to assure that we do not mess the input up
     * and also initialising the lists for each certain action
     * @param a ListofActions
     * @param u ListofUsers
     * @param m ListofMovies
     * @param ac ListofActors
     * @param s ListofSerials
     */
    public void getInputActions(final List<ActionInputData> a, final UserList u,
                                final MovieList m, final ActorList ac, final SerialList s) {
        if (a.size() != 0) {
            for (int i = 0; i < a.size(); i++) {
                Action action = new Action(a.get(i).getActionId(), a.get(i).getActionType(),
                        a.get(i).getType(), a.get(i).getUsername(), a.get(i).getObjectType(),
                        a.get(i).getSortType(), a.get(i).getCriteria(), a.get(i).getTitle(),
                        a.get(i).getGenre(), a.get(i).getNumber(), a.get(i).getGrade(),
                        a.get(i).getSeasonNumber(), a.get(i).getFilters(), u, m, ac, s);
                list.add(action);
            }
        }
    }

}
