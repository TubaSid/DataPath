package datapath
import chisel3._

class Pc extends Module {
    val io = IO(new Bundle {
        val pc_in = Input(SInt(32.W))
        val pc_out = Output(SInt(32.W))
        val pc_4 = Output(SInt(32.W))
    })

    val reg = RegInit(0.S(32.W))
    reg:=io.pc_in
    io.pc_out:=reg
    io.pc_4:=reg+4.S
}