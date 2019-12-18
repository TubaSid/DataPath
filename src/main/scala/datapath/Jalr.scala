package datapath
import chisel3._
class Jalr extends Module{
    val io=IO(new Bundle{
        val a=Input(SInt(32.W))
        val b=Input(SInt(32.W))
        val out=Output(SInt(32.W))
    })
    io.out:=(io.a+io.b)&(4294967294L.S)
}