package datapath
import chisel3._
class RegFile extends Module{
    val io=IO(new Bundle{
        val readreg1=Input(UInt(5.W))
        val readreg2=Input(UInt(5.W))
        val writereg=Input(UInt(5.W))
        val writedata=Input(SInt(32.W))
        val regwrite=Input(UInt(1.W))
        val readdata1=Output(SInt(32.W))
        val readdata2=Output(SInt(32.W))

    })
     val reg = RegInit(VecInit(Seq.fill(32)(0.S(32.W))))
    reg(0) := 0.S
    io.readdata1 := reg(io.readreg1) 
    io.readdata2 := reg(io.readreg2) 
    when(io.regwrite === 1.U) {
        when(io.writereg === "b00000".U) {
            reg(io.writereg) := 0.S    
        } 
        .otherwise {
            reg(io.writereg) := io.writedata
        }
}
}