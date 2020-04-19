package com.groupProject.ANPRAPI.Service;

import com.groupProject.ANPRAPI.Domain.Blacklist;

import java.util.List;

public interface BlacklistService {

    List<Blacklist> findAll();

    Boolean findForNumberPlate(String numberPlate);

    void delete(String numberPlate);

    void save(Blacklist blacklist);
}
