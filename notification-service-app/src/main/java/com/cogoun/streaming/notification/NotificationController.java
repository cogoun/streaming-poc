package com.cogoun.streaming.notification;

import com.cogoun.streaming.domain.Notification;
import com.cogoun.streaming.notification.exception.NotificationNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/notification")
public class NotificationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationController.class);

    @Autowired
    private NotificationRepository notificationRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<Notification> notifications() {
        return StreamSupport.stream(notificationRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @RequestMapping(value = "/{notificationId}", method = RequestMethod.GET)
    @ResponseBody
    public Notification get(@PathVariable long notificationId) {
        return notificationRepository
                .findById(String.valueOf(notificationId))
                .orElseThrow(() -> new NotificationNotFoundException(notificationId));
    }




}
