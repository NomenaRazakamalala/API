/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package packOutils;

import javax.sound.midi.MetaEventListener;

/**
 *
 * @author rakot
 */
public class SortieWS {
    private MetaWS meta = new MetaWS();
    private Object data;
    public SortieWS(){}
    public SortieWS(MetaWS meta, Object data) {
        this.meta = meta;
        this.data = data;
    }
    
    public MetaWS getMeta() {
        return meta;
    }

    public void setMeta(MetaWS meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString(){
        return this.data.toString();
    }
}
