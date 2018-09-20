package com.cogoun.streaming.task;

import com.cogoun.streaming.domain.Task;
import com.cogoun.streaming.task.exception.TaskNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/task")
public class TaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    @Autowired
    private TaskRepository taskRepository;

    @CrossOrigin
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Task> tasks() {
        return StreamSupport.stream(taskRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{taskId}", method = RequestMethod.GET)
    @ResponseBody
    public Task get(@PathVariable long taskId) {
        return taskRepository
                .findById(String.valueOf(taskId))
                .orElseThrow(() -> new TaskNotFoundException(taskId));
    }




}
