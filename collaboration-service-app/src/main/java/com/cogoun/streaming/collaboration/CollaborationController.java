package com.cogoun.streaming.collaboration;

import com.cogoun.streaming.collaboration.exception.CollaborationCannotBeStartedException;
import com.cogoun.streaming.domain.Collaboration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/collaboration")
public class CollaborationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CollaborationController.class);

    @Autowired
    private CollaborationEventProducer collaborationEventProducer;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public void create(@RequestBody Collaboration collaboration) {
        try {
            LOGGER.info("Incoming collaboration: " + collaboration.toString());
            collaborationEventProducer.produceStartCollaborationRoundEvent(collaboration);
        } catch (Exception e) {
            throw new CollaborationCannotBeStartedException(e);
        }
    }
}
