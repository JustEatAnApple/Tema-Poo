package main;

import fileio.SerialInputData;

import java.util.ArrayList;
import java.util.List;

public class SerialList {
    private ArrayList<Serial> list = new ArrayList<Serial>();

    public SerialList(){}

    @Override
    public String toString() {
        return "SerialList{" +
                "list=" + list +
                '}';
    }

    public String getTitle(int i) {
        return list.get(i).getTitle();
    }

    public int getSize() {
        return list.size();
    }


    public void addRatingbyTitle(String title, double r, int nr) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getTitle().equals(title)) {
                //list.get(i).getSeason(nr).
            }
        }
    }

    public Serial getSerialbyTitle(String title) {
        for (int i = 0; i < list.size(); i ++) {
            if (list.get(i).getTitle().equals(title)) {
                return list.get(i);
            }
        }
        return null;
    }

    public Serial getSerial(int i) {
        return list.get(i);
    }

    public void getInputSerials(List<SerialInputData> a) {
        if (a.size() != 0) {
            for (int i = 0; i < a.size(); i ++) {
                Serial serial = new Serial(a.get(i).getTitle(),  a.get(i).getCast(),
                        a.get(i).getGenres(), a.get(i).getNumberSeason(), a.get(i).getSeasons(),
                        a.get(i).getYear());
                list.add(serial);
            }
        }
    }

    public ArrayList<Serial> getList() {
        return list;
    }

    public void setList(ArrayList<Serial> list) {
        this.list = list;
    }
}
