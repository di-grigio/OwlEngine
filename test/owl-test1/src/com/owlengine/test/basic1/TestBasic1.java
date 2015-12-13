package com.owlengine.test.basic1;

import com.owlengine.OwlCycle;
import com.owlengine.OwlFrame;
import com.owlengine.config.OwlConfig;

/*
 * @title: Test Basic 1
 * @author: Leo-di-Grigio
 * @todo: Basic Owl project test
 * @lastcheck: 0.1.16
 */

public class TestBasic1 {

	public static void main(String [] args) {
		new OwlFrame(new Config(), new Cycle());
	}
}

class Config extends OwlConfig {
    
    @Override
    public String frameTitle() {
        return "OwlEngine - Test 1";
    }
}

class Cycle extends OwlCycle {

    @Override
    public void setup() {
        
    }
    
    @Override
    public void render() {
        
    }
    
    @Override
    public void dispose() {
        
    }
}