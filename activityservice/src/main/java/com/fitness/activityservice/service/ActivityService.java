package com.fitness.activityservice.service;

import com.fitness.activityservice.Repository.ActivityRepository;
import com.fitness.activityservice.dto.ActivityRequest;
import com.fitness.activityservice.dto.ActivityResponse;
import com.fitness.activityservice.model.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepository repository;
    private final UserValidationService userValidationService;

    public ActivityResponse trackActivity(ActivityRequest request) {

        boolean isValidUser = userValidationService.validateUser(request.getUserId());
        if(!isValidUser){
            throw new RuntimeException("User Invalid: "+request.getUserId());
        }
        Activity activity = Activity.builder()
                .userId(request.getUserId())
                .type(request.getType())
                .duration(request.getDuration())
                .caloriesBurned(request.getCaloriesBurned())
                .startTime(request.getStartTime())
                .additionalMetrics(request.getAdditionalMetrics())
                .build();

        Activity savedActivity = repository.save(activity);
        return mapToResponse(savedActivity);
    }
    private ActivityResponse mapToResponse(Activity activity){
         ActivityResponse activityResponse  = new ActivityResponse();
         activityResponse.setId(activity.getId());
         activityResponse.setType(activity.getType());
         activityResponse.setDuration(activity.getDuration());
         activityResponse.setCaloriesBurned(activity.getCaloriesBurned());
         activityResponse.setStartTime(activity.getStartTime());
         activityResponse.setAdditionalMetrics(activity.getAdditionalMetrics());
         activityResponse.setCreatedAt(activity.getCreatedAt());
         activityResponse.setUpdatedAt(activity.getUpdatedAt());

         return activityResponse;
    }

    public List<ActivityResponse> getUserActivities(String userId){
        List<Activity> activities = repository.findByUserId(userId);

        return activities.stream()
                .map(this:: mapToResponse)
                .collect(Collectors.toList());
    }
}
