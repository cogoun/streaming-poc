package com.cogoun.streaming.notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notification")
public class NotificationIndexingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationIndexingController.class);

    @Autowired
    private NotificationIndexingRepository notificationIndexingRepository;

}
