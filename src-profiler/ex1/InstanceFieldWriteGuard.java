package ex1;

import ch.usi.dag.disl.guard.GuardBase;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;

public class InstanceFieldWriteGuard extends GuardBase {
    @Override
    public boolean matches(AbstractInsnNode instruction) {
        // Detect instance field write (PUTFIELD opcode)
        return instruction.getOpcode() == Opcodes.PUTFIELD;
    }
}