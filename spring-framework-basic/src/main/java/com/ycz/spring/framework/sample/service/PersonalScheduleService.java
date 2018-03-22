package com.ycz.spring.framework.sample.service;

import com.ycz.spring.framework.sample.api.PersonalSchedule;
import com.ycz.spring.framework.sample.api.PersonalScheduleApi;

public interface PersonalScheduleService {

    public PersonalSchedule getScheduleBy(String id);

    public PersonalSchedule save(PersonalScheduleApi personalScheduleApi);
}
