package com.personal.movieapp;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.mockito.Mock;
import org.mockito.Mockito;

public class BaseTesting {

    public String description = "Default Description";

    @Mock
    public Context context = Mockito.mock(Context.class);

    @After
    public void sendDescription() {
        System.out.print(description + ":");
        description = "No description found";
    }

    @Before
    public void initBase() {

    }

}
