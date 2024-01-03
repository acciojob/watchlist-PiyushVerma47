package com.driver;

import org.springframework.stereotype.Repository;
import java.util.*;

@Repository

public class MovieRepository {

    HashMap<String, Movie> movieMap = new HashMap<>();
    HashMap<String, Director> directorMap = new HashMap<>();
    HashMap<String, List<String>> directorMovieMap = new HashMap<>();

    public void addMovie(Movie movie){
        String movieName = movie.getName();
        movieMap.put(movieName,movie);
    }

    public void addDirector(Director director){
        String directorName = director.getName();
        directorMap.put(directorName,director);
    }

    public void addMovieDirectorPair(String movie, String director){
        List<String> list = new ArrayList<>();
        if(directorMovieMap.containsKey(director)){
            list = directorMovieMap.get(director);
        }
        list.add(movie);
        directorMovieMap.put(director,list);
    }

    public Movie getMovieByName(String movieName) {
        return movieMap.get(movieName);
    }

    public Director getDirectorByName(String directorName) {
        return directorMap.get(directorName);
    }

    public List<String> getMoviesByDirectorName(String directorName) {
        return directorMovieMap.get(directorName);
    }

    public List<String> findAllMovies() {
        List<String> list = new ArrayList<>();
        for(String movieName : movieMap.keySet()){
            list.add(movieName);
        }
        return list;
    }

    public void deleteDirectorByName(String directorName) {

        if(directorMovieMap.containsKey(directorName)){
            List<String> movies = directorMovieMap.get(directorName);
            for(String movieName : movies){
                deleteByMovieName(movieName);
            }
            directorMovieMap.remove(directorName);
        }
        directorMap.remove(directorName);


    }

    public void deleteAllDirectors() {

        List<String> directors = new ArrayList<>();
        for(String directorName : directorMap.keySet()){
            directors.add(directorName);
        }

        for(String directorName : directors){
            if(directorMovieMap.containsKey(directorName)){
                List<String> movies = directorMovieMap.get(directorName);
                for(String movieNames : movies){
                    deleteByMovieName(movieNames);
                }
            }
        }

        directorMap.clear();
        directorMovieMap.clear();
    }

    public void deleteByMovieName(String movieName) {
        if(movieMap.containsKey(movieName)){
            movieMap.remove(movieName);
        }
    }
}
