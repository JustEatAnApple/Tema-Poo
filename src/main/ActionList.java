package main;

import fileio.ActionInputData;

import java.util.ArrayList;
import java.util.List;

public class ActionList {
    private ArrayList<Action> list = new ArrayList<Action>();

    public int getSize() {
        return list.size();
    }

    public Action getAction(int i) {
        return list.get(i);
    }

    public void getInputActions(List<ActionInputData> a, UserList u, MovieList m, ActorList ac,
                                SerialList s) {
        if (a.size() != 0) {
            for (int i = 0; i < a.size(); i ++) {
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
