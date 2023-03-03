    mov EAX 20
    mov EBX 1
    mov ECX 1
    mov ESI 310795
    mov EDI 1
l1: mov ESI 0
    add ESI ECX
    add ECX EBX
    mov EBX 0
    add EBX ESI
    out ECX
    sub EAX EDI
    jnz EAX l1
    mov EDI 060700
    out EDI