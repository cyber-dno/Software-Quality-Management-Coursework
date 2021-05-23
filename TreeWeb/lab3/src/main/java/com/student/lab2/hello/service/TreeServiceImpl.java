package com.student.lab2.hello.service;

import com.student.lab2.hello.source.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private static final Tree<Integer, Integer> tree = new Tree<>();

    @Override
    public void AddData(int dc) { tree.put(dc, 0); }

    @Override
    public String GetData() { return tree.printTree(); }

    @Override
    public void DelData(int index) { tree.remove(index); }
}
