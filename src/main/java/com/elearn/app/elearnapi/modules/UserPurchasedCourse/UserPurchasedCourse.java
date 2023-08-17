package com.elearn.app.elearnapi.modules.UserPurchasedCourse;

import java.time.LocalTime;
import java.time.ZoneOffset;

import com.elearn.app.elearnapi.modules.Course.Course;
import com.elearn.app.elearnapi.modules.PaymentTransaction.PaymentTransaction;
import com.elearn.app.elearnapi.modules.User.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class UserPurchasedCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private Double coursePrice;

    @OneToOne
    @JoinColumn(name = "payment_transaction_id")
    private PaymentTransaction paymentTransaction;

    private LocalTime purchasedAt;

    public UserPurchasedCourse() {
        this.purchasedAt = LocalTime.now(ZoneOffset.UTC);
    }

    public UserPurchasedCourse(User user, Course course, PaymentTransaction paymentTransaction) {
        this.course = course;
        this.user = user;
        this.coursePrice = course.getPrice();
        this.paymentTransaction = paymentTransaction;
        this.purchasedAt = LocalTime.now(ZoneOffset.UTC);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PaymentTransaction getPaymentTransaction() {
        return paymentTransaction;
    }

    public void setPaymentTransaction(PaymentTransaction paymentTransaction) {
        this.paymentTransaction = paymentTransaction;
    }

    public Double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Double coursePrice) {
        this.coursePrice = coursePrice;
    }

    public LocalTime getPurchasedAt() {
        return purchasedAt;
    }

    public void setPurchasedAt(LocalTime purchasedAt) {
        this.purchasedAt = purchasedAt;
    }
}
