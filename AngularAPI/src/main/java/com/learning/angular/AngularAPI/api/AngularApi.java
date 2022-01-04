package com.learning.angular.AngularAPI.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.learning.angular.AngularAPI.domain.Task;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AngularApi {

    private static int count = 3;
    private static List<Task> tasksList = new ArrayList<>(Arrays.asList(
            new Task(1, "Doctors Appointment", "May 5th at 2:30pm", true),
            new Task(2, "Meeting at School", "May 6th at 1:30pm", true),
            new Task(3, "Food Shopping", "May 7th at 12:30pm", false),
            new Task(4, "Hanging out with friends", "May 7th at 08:15pm", false)
    ));

    @GetMapping(value = "/task", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public List<Task> getTasks() {
        System.out.println("Loading all tasks ..");
        return tasksList;
    }

    @GetMapping(value = "/task/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public Task getTask(@PathVariable Integer id) {

        System.out.println("Loading task " + id + " ..");
        Optional<Task> task = tasksList.stream().filter(t -> id.equals(t.getId())).findAny();
        return task.isPresent() ? task.get() : null;
    }

    @PostMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public Task createTask(@RequestBody Task task) {

        System.out.println("CREATING task " + task + " ..");
        task.setId(++count);
        tasksList.add(task);
        return task;
    }

    @PutMapping(value = "/task", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public Task updateTask(@RequestBody Task task) {

        System.out.println("UPDATING task " + task + " ..");
        Optional<Task> oldTask = tasksList.stream().filter(t -> task.getId().equals(t.getId())).findAny();
        if (oldTask.isPresent()) {

            tasksList.remove(oldTask.get());
            tasksList.add(task);
        }
        return task;
    }

    @DeleteMapping(value = "/task/{id}")
    @CrossOrigin(origins = "*")
    public Integer deleteTask(@PathVariable Integer id) {

        List<Task> list = new ArrayList<>();
        list.addAll(tasksList);

        Optional<Task> oldTask = list.stream().filter(t -> id.equals(t.getId())).findAny();
        if (oldTask.isPresent()) {
            //tasksList.remove(tasksList.indexOf(oldTask.get()));
            list.remove(oldTask.get());
        }
        tasksList = list;
        return id;
    }

    public static List<Integer> rotLeft(List<Integer> a, int d) {

        Integer [] arr = a.toArray(new Integer[] {});
        int first = arr[0];
        int i = 0;
        for (;i < arr.length - 1; i++) {

            arr[i] = arr[i + 1];
        }
        arr[i] = first;
        return Arrays.asList(arr);
    }

}
