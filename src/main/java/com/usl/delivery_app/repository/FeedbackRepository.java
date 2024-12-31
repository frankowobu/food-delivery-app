package com.usl.delivery_app.repository;

import com.usl.delivery_app.data.Feedback.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
}
