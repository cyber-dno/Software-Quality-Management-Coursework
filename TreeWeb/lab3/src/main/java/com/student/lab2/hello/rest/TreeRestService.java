package com.student.lab2.hello.rest;

import com.student.lab2.hello.service.TreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public/rest/tree")
public class TreeRestService {
    @Autowired
    private TreeService treeService;
    
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Response> brows() {
        Response r = new Response(treeService.GetData());
        return ResponseEntity.ok(r);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        int value = Integer.parseInt(id);
        treeService.DelData(value); }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void add(@PathVariable("id") String id) {
        int value = Integer.parseInt(id);
        treeService.AddData(value); }
}
