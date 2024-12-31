package com.usl.delivery_app.service.FeedbackService;

import com.usl.delivery_app.data.Feedback.Feedback;
import com.usl.delivery_app.repository.FeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FeedBackServiceImpl  implements FeedbackService{

    private FeedbackRepository feedbackRepository;

    @Override
    public Feedback getFeedbackById(Long id) {
        Feedback feedback = feedbackRepository.findById(id).orElse(null);
        return feedback;
    }

    @Override
    public Feedback createFeedback(Feedback feedback) {
        Feedback newFeedback = feedbackRepository.save(feedback);
        return newFeedback;
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        List<Feedback> feedback = feedbackRepository.findAll();
        return feedback;
    }
}
