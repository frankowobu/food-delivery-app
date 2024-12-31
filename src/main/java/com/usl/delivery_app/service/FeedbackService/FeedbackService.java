package com.usl.delivery_app.service.FeedbackService;


import com.usl.delivery_app.data.Feedback.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback getFeedbackById(Long id);
    Feedback createFeedback(Feedback feedback);

    List<Feedback> getAllFeedbacks();
}
