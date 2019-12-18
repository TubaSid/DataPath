package datapath
import chisel3._
import chisel3.util.Cat
class AluControl extends Module{
    val io=IO(new Bundle{
        val aluop=Input(UInt(3.W))
        val func3=Input(UInt(3.W))
        val func7=Input(UInt(1.W))
        val alucntrl=Output(UInt(5.W))
    })
    when(io.aluop==="b000".U )
    {
        io.alucntrl:=Cat("b0".U,io.func7,io.func3)
    }
    .elsewhen(io.aluop==="b100".U){
        io.alucntrl:="b00000".U
    }
    .elsewhen(io.aluop==="b101".U  ){
        io.alucntrl:="b00000".U
    }
    .elsewhen(io.aluop==="b010".U ){
        io.alucntrl:=Cat("b10".U,io.func3)
    }
    .elsewhen(io.aluop==="b001".U ){
        when(io.func3==="b101".U){
            io.alucntrl:=Cat("b0".U,io.func7,io.func3)
        }
        .otherwise{
        io.alucntrl:=Cat("b00".U,io.func3)}
    }
    .elsewhen(io.aluop==="b011".U){
        io.alucntrl:="b11111".U
    }
    .elsewhen(io.aluop==="b110".U){
        io.alucntrl:="b00000".U
    }
    .otherwise{
        io.alucntrl:=DontCare
    }
}