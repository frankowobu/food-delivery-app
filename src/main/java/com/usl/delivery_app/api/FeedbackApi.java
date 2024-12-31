package com.usl.delivery_app.api;

import com.usl.delivery_app.data.Feedback.Feedback;
import com.usl.delivery_app.service.FeedbackService.FeedBackServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback")
@AllArgsConstructor
public class FeedbackApi {
    private FeedBackServiceImpl feedbackService;

    @GetMapping("/{id}")
    public ResponseEntity<Feedback> getFeedback(@PathVariable Long id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        if (feedback == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Feedback with id " + id + " not found");
        }
        return ResponseEntity.ok(feedback);
    }

    @PostMapping("/create")
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        try {
            Feedback newFeedback = feedbackService.createFeedback(feedback);
            return ResponseEntity.ok(newFeedback);
        }catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR, "Error occurred while creating feedback: " + e.getMessage(), e);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedback = feedbackService.getAllFeedbacks();
        if (feedback.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "No feedback found");
        }
        return ResponseEntity.ok(feedback);
    }
}