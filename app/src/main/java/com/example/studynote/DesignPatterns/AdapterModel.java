package com.example.studynote.DesignPatterns;

/**
 * 适配器模式
 * <p>
 * 该模式是将两个不相干的接口进行适配，从而在一起工作
 * 比如hdmi接口转vga，请看示例
 */
public class AdapterModel {

    /**
     * 定义HDMI接口
     */
    public interface HDMI {
        void hdmiwork();
    }

    /**
     * 定义VGA接口
     */
    public interface VGA {
        void vgawork();
    }


    /**
     * 定义VGA转HDMI接口
     * 在vga的接口工作处调用hdmi工作方法，完成适配转换
     */
    public class vgaToHDMI implements VGA {

        private HDMI hdmi;

        public vgaToHDMI(HDMI hdmi) {
            this.hdmi = hdmi;
        }

        @Override
        public void vgawork() {
            //vga接口工作直接执行hdmi接口的工作
            hdmi.hdmiwork();
        }
    }


    /*---------------上述是接口之间的转化，有些适配更加简单，比如利用继承的特性-----------------------------*/

    /**
     * 定义一个电脑类
     * 这个电脑有hdmi接口，没有vga接口
     */
    public class Computer {
        public void hdmiwork() {
            /*--------hdmi工作-----*/
        }
    }

    /**
     * 定义一个vga转hdmi接口的适配器
     * 这样电脑就有
     */
    public class vgaToHDMI2Computer extends Computer implements VGA {

        @Override
        public void vgawork() {
            //vga接口工作直接执行hdmi接口的工作
            hdmiwork();
        }
    }

    /**
     * 电脑也同时拥有vga和hdmi接口
     */
    public class RealClient {
        public void main(String[] args) {
            vgaToHDMI2Computer computer = new vgaToHDMI2Computer();
            computer.vgawork();
            computer.hdmiwork();
        }
    }


}
