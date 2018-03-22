package com.ycz.spring.framework.sample.api;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.util.Date;

public class PersonalSchedule implements PersonalScheduleApi {

    private String who;
    private Date when;
    private String what;
    private String why;
    private String how;

    @Override
    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Override
    public Date getWhen() {
        return when;
    }

    public void setWhen(Date when) {
        this.when = when;
    }

    @Override
    public String getWhat() {
        return what;
    }

    public void setWhat(String what) {
        this.what = what;
    }

    @Override
    public String getWhy() {
        return why;
    }

    public void setWhy(String why) {
        this.why = why;
    }

    @Override
    public String getHow() {
        return how;
    }

    public void setHow(String how) {
        this.how = how;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonalSchedule that = (PersonalSchedule) o;
        return Objects.equal(who, that.who) &&
            Objects.equal(when, that.when) &&
            Objects.equal(what, that.what) &&
            Objects.equal(why, that.why) &&
            Objects.equal(how, that.how);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(who, when, what, why, how);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("who", who)
            .add("when", when)
            .add("what", what)
            .add("why", why)
            .add("how", how)
            .toString();
    }
}
