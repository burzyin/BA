package com.exadel.bsgdemo.data;

import java.util.List;

/**
 * Developer: Paulau Aliaksandr
 * Created: 9:24 AM, 10/14/13
 */

@SuppressWarnings("UnusedDeclaration")
public class Profile {
    private long id;
    private List<Place> recommendedPlaces;
    private List guides;

    private int visited;
    private int liked;
    private int recommended;

    public Profile(long id, List<Place> recommendedPlaces, List guides, int visited, int liked, int recommended) {
        this.id = id;
        this.recommendedPlaces = recommendedPlaces;
        this.guides = guides;
        this.visited = visited;
        this.liked = liked;
        this.recommended = recommended;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Place> getRecommendedPlaces() {
        return recommendedPlaces;
    }

    public void setRecommendedPlaces(List<Place> recommendedPlaces) {
        this.recommendedPlaces = recommendedPlaces;
    }

    public List getGuides() {
        return guides;
    }

    public void setGuides(List guides) {
        this.guides = guides;
    }

    public int getVisited() {
        return visited;
    }

    public void setVisited(int visited) {
        this.visited = visited;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }

    public int getRecommended() {
        return recommended;
    }

    public void setRecommended(int recommended) {
        this.recommended = recommended;
    }
}
