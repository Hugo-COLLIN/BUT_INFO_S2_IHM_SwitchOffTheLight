package com.example.switchOffTheLight.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GridModelTest {

    GridModel model;

    @BeforeEach
    void beforeEach ()
    {
        //Initialisation
        this.model = new GridModel();
    }

    @Test
    void test_clickOnLight_allFalse()
    {
        //Tests
        for (int i = 0 ; i < GridModel.LENGTH_X ; i ++)
            for (int j = 0 ; j < GridModel.LENGTH_Y ; j ++)
                assertFalse(this.model.getLight(i, j),"Should be false");
    }

    @Test
    void test_invert_in()
    {
        //Method call
        this.model.invert(0,0);

        //Tests
        assertTrue(this.model.getLight(0, 0), "Should be true");
    }

    @Test
    void test_invert_out()
    {
        //Method call
        this.model.invert(GridModel.LENGTH_X + 3,GridModel.LENGTH_X - 2);

        //Tests
        for (int i = 0 ; i < GridModel.LENGTH_X ; i ++)
            for (int j = 0 ; j < GridModel.LENGTH_Y ; j ++)
                assertFalse(this.model.getLight(i, j),"Should be false");
    }

    @Test
    void test_inGrid_in() {
        //Method call
        boolean res = this.model.inGrid(1,2);

        //Tests
        assertTrue(res, "Should be true");
    }

    @Test
    void test_inGrid_out() {
        //Method call
        boolean res = this.model.inGrid(GridModel.LENGTH_X + 3,GridModel.LENGTH_X - 2);

        //Tests
        assertFalse(res, "Should be false");
    }
}