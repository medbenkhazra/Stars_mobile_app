package com.example.recycle.service;

import com.example.recycle.beans.Star;
import com.example.recycle.dao.IDao;

import java.util.ArrayList;
import java.util.List;

public class StarService implements IDao<Star> {
    private List<Star> stars;
    private static StarService instance;

    private StarService() {
        this.stars = new ArrayList<>();
    }

    @Override
    public boolean create(Star o) {
        return stars.add(o);
    }

    @Override
    public boolean update(Star o) {
        for(Star s : stars){
            if(s.getStar() == o.getId()){
                s.setImg(o.getImg());
                s.setName(o.getName());
                s.setStar(o.getStar());
            }
        }
        return true;


    }

    @Override
    public boolean delete(Star o) {
        return stars.remove(o);
    }

    @Override
    public Star findById(int id) {
        for (Star s : this.stars){
            if (s.getId()==id){
                return s;
            }
        }
        return null;
    }

    @Override
    public List<Star> findAll() {
        return stars;
    }


    public static StarService getInstance() {
        if(instance == null)
            instance = new StarService();
        return instance;
    }
}
