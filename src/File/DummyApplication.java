/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package File;

import Base.Handler;

/**
 *
 * @author Bailey
 */
public class DummyApplication implements IApplication{

    @Override
    public void Launch() {
        TextEngine.TextEngine.addMessage(new String[]{"This is a dummy application, so it dose nothing."});
    }
    
}
