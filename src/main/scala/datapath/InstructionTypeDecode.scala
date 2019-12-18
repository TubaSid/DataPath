package datapath
import chisel3._
class InstructionTypeDecode extends Module{
    val io=IO(new Bundle{
    val opcode=Input(UInt(7.W))
    val rtype=Output(UInt(1.W))
    val loadtype=Output(UInt(1.W))
    val stype=Output(UInt(1.W))
    val sbtype=Output(UInt(1.W))
    val itype=Output(UInt(1.W))
    val jalrtype=Output(UInt(1.W))
    val jaltype=Output(UInt(1.W))
    val luitype=Output(UInt(1.W))

 })
 io.rtype:=0.U
 io.loadtype:=0.U
 io.stype:=0.U
 io.sbtype:=0.U
 io.itype:=0.U
 io.jalrtype:=0.U
 io.jaltype:=0.U
 io.luitype:=0.U

 when(io.opcode==="b0110011".U){
    io.rtype:=1.U
 }
 .elsewhen(io.opcode==="b0000011".U){
    io.loadtype:=1.U
 }
 .elsewhen(io.opcode==="b0100011".U){
    io.stype:=1.U
 }
 .elsewhen(io.opcode==="b1100011".U){
    io.sbtype:=1.U
 }
 .elsewhen(io.opcode==="b0010011".U){
    io.itype:=1.U
 }
 .elsewhen(io.opcode==="b0000011".U){
    io.jalrtype:=1.U
 }
 .elsewhen(io.opcode==="b1101111".U){
    io.jaltype:=1.U
 }
 .elsewhen(io.opcode==="b0110111".U){
    io.luitype:=1.U
 }

}