package ex1;

import ch.usi.dag.disl.guard.GuardBase;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;

public class InstanceFieldReadGuard extends GuardBase {
    @Override
    public boolean matches(AbstractInsnNode instruction) {
        // Detect instance field read (GETFIELD opcode)
        return instruction.getOpcode() == Opcodes.GETFIELD;
    }
}