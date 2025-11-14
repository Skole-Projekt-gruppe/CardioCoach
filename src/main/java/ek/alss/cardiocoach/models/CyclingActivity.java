package ek.alss.cardiocoach.models;

import java.time.ZonedDateTime;

public class CyclingActivity {

    public long id;
    public ZonedDateTime start_date_local;
    public String name;

    public double distance; // meter
    public int moving_time; // seconds
    public int elapsed_time; // seconds
    public double total_elevation_gain; // meter
    public String type;

    public double average_speed; // m/s
    public double max_speed;     // m/s
    public double average_cadence; // rpm

    public double average_watts;
    public double max_watts;
    public double weighted_average_watts;
    public double kilojoules;

    public boolean has_heartrate;
    public double average_heartrate;
    public double max_heartrate;

    public double elev_high;
    public double elev_low;

    public CyclingActivity() {
    }

    public CyclingActivity(double max_speed, long id, ZonedDateTime start_date_local, String name, double distance, int moving_time, int elapsed_time, double total_elevation_gain, String type, double average_speed, double average_cadence, double average_watts, double max_watts, double weighted_average_watts, double kilojoules, boolean has_heartrate, double average_heartrate, double max_heartrate, double elev_high, double elev_low) {
        this.max_speed = max_speed;
        this.id = id;
        this.start_date_local = start_date_local;
        this.name = name;
        this.distance = distance;
        this.moving_time = moving_time;
        this.elapsed_time = elapsed_time;
        this.total_elevation_gain = total_elevation_gain;
        this.type = type;
        this.average_speed = average_speed;
        this.average_cadence = average_cadence;
        this.average_watts = average_watts;
        this.max_watts = max_watts;
        this.weighted_average_watts = weighted_average_watts;
        this.kilojoules = kilojoules;
        this.has_heartrate = has_heartrate;
        this.average_heartrate = average_heartrate;
        this.max_heartrate = max_heartrate;
        this.elev_high = elev_high;
        this.elev_low = elev_low;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ZonedDateTime getStart_date_local() {
        return start_date_local;
    }

    public void setStart_date_local(ZonedDateTime start_date_local) {
        this.start_date_local = start_date_local;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getMoving_time() {
        return moving_time;
    }

    public void setMoving_time(int moving_time) {
        this.moving_time = moving_time;
    }

    public int getElapsed_time() {
        return elapsed_time;
    }

    public void setElapsed_time(int elapsed_time) {
        this.elapsed_time = elapsed_time;
    }

    public double getTotal_elevation_gain() {
        return total_elevation_gain;
    }

    public void setTotal_elevation_gain(double total_elevation_gain) {
        this.total_elevation_gain = total_elevation_gain;
    }

    public double getAverage_speed() {
        return average_speed;
    }

    public void setAverage_speed(double average_speed) {
        this.average_speed = average_speed;
    }

    public double getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(double max_speed) {
        this.max_speed = max_speed;
    }

    public double getAverage_cadence() {
        return average_cadence;
    }

    public void setAverage_cadence(double average_cadence) {
        this.average_cadence = average_cadence;
    }

    public double getAverage_watts() {
        return average_watts;
    }

    public void setAverage_watts(double average_watts) {
        this.average_watts = average_watts;
    }

    public double getMax_watts() {
        return max_watts;
    }

    public void setMax_watts(double max_watts) {
        this.max_watts = max_watts;
    }

    public double getWeighted_average_watts() {
        return weighted_average_watts;
    }

    public void setWeighted_average_watts(double weighted_average_watts) {
        this.weighted_average_watts = weighted_average_watts;
    }

    public double getKilojoules() {
        return kilojoules;
    }

    public void setKilojoules(double kilojoules) {
        this.kilojoules = kilojoules;
    }

    public boolean isHas_heartrate() {
        return has_heartrate;
    }

    public void setHas_heartrate(boolean has_heartrate) {
        this.has_heartrate = has_heartrate;
    }

    public double getAverage_heartrate() {
        return average_heartrate;
    }

    public void setAverage_heartrate(double average_heartrate) {
        this.average_heartrate = average_heartrate;
    }

    public double getMax_heartrate() {
        return max_heartrate;
    }

    public void setMax_heartrate(double max_heartrate) {
        this.max_heartrate = max_heartrate;
    }

    public double getElev_high() {
        return elev_high;
    }

    public void setElev_high(double elev_high) {
        this.elev_high = elev_high;
    }

    public double getElev_low() {
        return elev_low;
    }

    public void setElev_low(double elev_low) {
        this.elev_low = elev_low;
    }
}
