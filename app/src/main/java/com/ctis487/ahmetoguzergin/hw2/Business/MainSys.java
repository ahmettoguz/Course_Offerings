package com.ctis487.ahmetoguzergin.hw2.Business;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.ctis487.ahmetoguzergin.hw2.Models.Course;
import com.ctis487.ahmetoguzergin.hw2.Models.Person;
import com.ctis487.ahmetoguzergin.hw2.Models.Section;
import com.ctis487.ahmetoguzergin.hw2.Models.Student;
import com.ctis487.ahmetoguzergin.hw2.Models.Teacher;
import com.ctis487.ahmetoguzergin.hw2.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainSys {

    public static ArrayList<Person> persons = new ArrayList<>();
    public static ArrayList<Course> courses = new ArrayList<>();

    public static void prepareData() {
        Map<String, String> takenCourses = new HashMap<String, String>();
        takenCourses.put("151", "1");
        takenCourses.put("255", "2");
        Student s1 = new Student("Ahmet", "ahmet@hotmail.com", "ahmet", takenCourses);

        takenCourses = new HashMap<String, String>();
        takenCourses.put("151", "1");
        Student s2 = new Student("Zeynep", "zeynep@hotmail.com", "zeynep", takenCourses);

        takenCourses = new HashMap<String, String>();
        takenCourses.put("166", "2");
        takenCourses.put("487", "1");
        Student s3 = new Student("Tuna", "tuna@hotmail.com", "tuna", takenCourses);

        takenCourses = new HashMap<String, String>();
        takenCourses.put("496", "1");
        takenCourses.put("487", "1");
        Student s4 = new Student("Sena", "sena@hotmail.com", "sena", takenCourses);

        Teacher t1 = new Teacher("Nese", "nese@hotmail.com", "nese");
        Teacher t2 = new Teacher("ali", "ali@hotmail.com", "ali");

        // persons
        Collections.addAll(persons, s1, s2, s3, s4, t1, t2);

        ArrayList<Section> sections = new ArrayList<>();

        Section s = new Section("nese", 1);
        sections.add(s);
        s = new Section("nese", 2);
        sections.add(s);
        s = new Section("ali", 3);
        sections.add(s);

        //courses with sections
        Course c1 = new Course("151", "Introduction to Programming", sections, "An introduction to programming from both design and programming standpoints. Syntax and semantics of programming languages, program debugging and testing...", R.drawable.ctis151, 1, 7, 30, 25, 4, true);

        sections = new ArrayList<>();
        s = new Section("nese", 1);
        sections.add(s);
        s = new Section("ali", 2);
        sections.add(s);

        Course c2 = new Course("166", "Information Technologies", sections, "The basic operating system concepts by using Linux operating system. Linux GUI, kernel, CUI, shells, basic shell programming...", R.drawable.ctis166, 1, 5, 20, 20, 3, true);

        sections = new ArrayList<>();
        Course c3 = new Course("262", "Applied Computer Networks", sections, "This course covers Switching, Routing and Wireless Essentials related topics. The coverd topics include the architecture...", R.drawable.ctis162, 2, 5, 18, 16, 4, true);

        sections = new ArrayList<>();
        s = new Section("nese", 1);
        sections.add(s);
        s = new Section("nese", 2);
        sections.add(s);
        Course c4 = new Course("255", "Frontend Web Technologies", sections, "The necessary background information and the technologies to develop and maintain a professional web site. The design...", R.drawable.ctis255, 2, 3, 29, 25, 3, false);

        sections = new ArrayList<>();
        s = new Section("nese", 1);
        sections.add(s);
        Course c5 = new Course("487", "Mobile Application Development", sections, "Technical and business related challenges posed by current mobile devices and wireless...", R.drawable.ctis487, 3, 5, 24, 24, 4, true);

        sections = new ArrayList<>();
        s = new Section("ali", 1);
        sections.add(s);
        Course c6 = new Course("496", "Computer and Network Security", sections, "Theory and practice of computer security, focusing in particular on the security aspects of computing systems. Survey of classical...", R.drawable.ctis496, 4, 3, 18, 17, 3, false);

        sections = new ArrayList<>();
        s = new Section("ali", 1);
        sections.add(s);
        s = new Section("ali", 2);
        sections.add(s);
        Course c7 = new Course("473", "Cloud Computing Foundations", sections, "Cloud concepts overview. Cloud economics and billing. Overview of cloud security, architecture, pricing and support...", R.drawable.ctis473, 4, 3, 23, 17, 3, false);

        Collections.addAll(courses, c1, c2, c3, c4, c5, c6, c7);
    }

    public static void animateTextView(Context ctx, TextView tvTarget) {
        int myColor = ContextCompat.getColor(ctx, R.color.teal_700);
        int myAnotherColor = Color.rgb(231, 233, 235);

        // color animation of the text
        ValueAnimator colorAnim = ObjectAnimator.ofInt(tvTarget, "textColor", myColor, Color.RED, Color.BLUE);
        colorAnim.setDuration(2000);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.setRepeatCount(ValueAnimator.INFINITE);
        colorAnim.setRepeatMode(ValueAnimator.REVERSE);
        colorAnim.start();

        // blink animation of the text
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(3000);
        //anim.setStartOffset(200);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        tvTarget.startAnimation(anim);
    }

    public static Person loginEvent(Context ctx, String eMail, String password) {
        //check username and password return person object
        for (Person person : persons) {
            if (person.geteMail().equalsIgnoreCase(eMail)) {
                if (person.getPassword().equalsIgnoreCase(password)) {
                    if (person instanceof Student)
                        return (Student) person;
                    else
                        return (Teacher) person;
                } else {
                    msg(ctx, "Login Failed!");
                    return null;
                }
            }
        }

        msg(ctx, "Login Failed!");
        return null;
    }

    public static void msg(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    public static void directToMainPage(Context ctx, Class target, Person person) {
        // intent operation to send person object
        Intent sendIntent = new Intent(ctx, target);
        sendIntent.putExtra("id", person.getId());
        ctx.startActivity(sendIntent);
    }

    public static ArrayList<Person> getPersons() {
        return persons;
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }
}
