package datapath
import chisel3._
class ControlDecode extends Module{
    val io=IO(new Bundle{
        val rtype=Input(UInt(1.W))
        val loadtype=Input(UInt(1.W))
        val stype=Input(UInt(1.W))
        val sbtype=Input(UInt(1.W))
        val itype=Input(UInt(1.W))
        val jalrtype=Input(UInt(1.W))
        val jaltype=Input(UInt(1.W))
        val luitype=Input(UInt(1.W))
        
        val memwrite=Output(UInt(1.W))
        val branch=Output(UInt(1.W))
        val memread=Output(UInt(1.W))
        val regwrite=Output(UInt(1.W))
        val memtoreg=Output(UInt(1.W))
        val aluop=Output(UInt(3.W))
        val operand_a_sel=Output(UInt(2.W))
        val operand_b_sel=Output(UInt(1.W))
        val extend_sel=Output(UInt(2.W))
        val next_pc_sel=Output(UInt(2.W))
    })
    
    io.memwrite:=0.U
    io.branch:=0.U
    io.memread:=0.U
    io.regwrite:=0.U
    io.memtoreg:=0.U
    io.aluop:=0.U
    io.operand_a_sel:=0.U
    io.operand_b_sel:=0.U
    io.extend_sel:=0.U
    io.next_pc_sel:=0.U
    when(io.rtype===1.U)
    {
         io.memwrite:=0.U
         io.branch:=0.U
         io.memread:=0.U
         io.regwrite:=1.U
         io.memtoreg:=0.U
         io.aluop:="b000".U
         io.operand_a_sel:="b00".U
         io.operand_b_sel:="b0".U
         io.extend_sel:="b00".U
         io.next_pc_sel:="b00".U
    }
    .elsewhen(io.loadtype===1.U)
    {
         io.memwrite:=0.U
         io.branch:=0.U
         io.memread:=1.U
         io.regwrite:=1.U
         io.memtoreg:=1.U
         io.aluop:="b100".U
         io.operand_a_sel:="b00".U
         io.operand_b_sel:="b1".U
         io.extend_sel:="b00".U
         io.next_pc_sel:="b00".U
    }
    .elsewhen(io.stype===1.U)
    {
         io.memwrite:=1.U
         io.branch:=0.U
         io.memread:=0.U
         io.regwrite:=0.U
         io.memtoreg:=0.U
         io.aluop:="b101".U
         io.operand_a_sel:="b00".U
         io.operand_b_sel:="b1".U
         io.extend_sel:="b10".U
         io.next_pc_sel:="b00".U
    }
    .elsewhen(io.sbtype===1.U)
    {
         io.memwrite:=0.U
         io.branch:=1.U
         io.memread:=0.U
         io.regwrite:=0.U
         io.memtoreg:=0.U
         io.aluop:="b010".U
         io.operand_a_sel:="b00".U
         io.operand_b_sel:="b0".U
         io.extend_sel:="b00".U
         io.next_pc_sel:="b01".U
    }
    .elsewhen(io.itype===1.U)
    {
         io.memwrite:=0.U
         io.branch:=0.U
         io.memread:=0.U
         io.regwrite:=1.U
         io.memtoreg:=0.U
         io.aluop:="b001".U
         io.operand_a_sel:="b00".U
         io.operand_b_sel:="b1".U
         io.extend_sel:="b00".U
         io.next_pc_sel:="b00".U
    }
    .elsewhen(io.jalrtype===1.U)
    {
         io.memwrite:=0.U
         io.branch:=0.U
         io.memread:=0.U
         io.regwrite:=1.U
         io.memtoreg:=0.U
         io.aluop:="b011".U
         io.operand_a_sel:="b10".U
         io.operand_b_sel:="b0".U
         io.extend_sel:="b00".U
         io.next_pc_sel:="b11".U
    }
    .elsewhen(io.jaltype===1.U)
    {
         io.memwrite:=0.U
         io.branch:=0.U
         io.memread:=0.U
         io.regwrite:=1.U
         io.memtoreg:=0.U
         io.aluop:="b011".U
         io.operand_a_sel:="b10".U
         io.operand_b_sel:="b0".U
         io.extend_sel:="b00".U
         io.next_pc_sel:="b10".U
    }
    .elsewhen(io.luitype===1.U)
    {
         io.memwrite:=0.U
         io.branch:=0.U
         io.memread:=0.U
         io.regwrite:=1.U
         io.memtoreg:=0.U
         io.aluop:="b110".U
         io.operand_a_sel:="b11".U
         io.operand_b_sel:="b1".U
         io.extend_sel:="b01".U
         io.next_pc_sel:="b00".U
    }
    .otherwise
    {
         io.memwrite:=0.U
         io.branch:=0.U
         io.memread:=0.U
         io.regwrite:=0.U
         io.memtoreg:=0.U
         io.aluop:="b000".U
         io.operand_a_sel:="b00".U
         io.operand_b_sel:="b0".U
         io.extend_sel:="b00".U
         io.next_pc_sel:="b00".U
    }
}



