package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class Recommendation {
    private ActorList a;
    private VideoList v;
    private UserList u;

    public Recommendation(ActorList a, VideoList v, UserList u) {
        this.a = a;
        this.v = v;
        this.u = u;
    }

    public String recommendStandard(User u) {
        String checkthis = "";
        for (Video vd : v.getList()) {
            int ok = 1;
            for (Map.Entry<String, Integer> entry : u.getHistory().entrySet()) {
                String k = entry.getKey();
                Integer v = entry.getValue();
                if (k.equals(vd.getName())) {
                    ok = 0;
                    break;
                }
            }
            if (ok == 1) {
                checkthis = vd.getName();
                return checkthis;
            }
        }
        return checkthis;
    }

    public ArrayList<Video> getBestVidz(User u) {
        ArrayList<Video> vidz = new ArrayList<Video>();
        for (Video vod : v.getList()) {
            vidz.add(vod);
        }
        Comparator<Video> cmpprio = Comparator.comparing(Video::getPriority);
        Collections.sort(vidz, cmpprio);
        Comparator<Video> cmpvdR = Comparator.comparing(Video::getRating);
        Collections.sort(vidz, cmpvdR);
        return vidz;
    }

    public String recommendBestUnseen(User u) {
        String checkthis = "";
        ArrayList<Video> videos = getBestVidz(u);
        for (int i = videos.size() - 1; i >= 0; i--) {
            int ok = 0;
            for (Map.Entry<String, Integer> entry : u.getHistory().entrySet()) {
                String k = entry.getKey();
                Integer v = entry.getValue();
                if (k.equals(videos.get(i).getName())) {
                    ok = 1;
                    break;
                }
            }
            if (ok == 0) {
                checkthis = videos.get(i).getName();
                return checkthis;
            }
        }
        return checkthis;
    }

}
