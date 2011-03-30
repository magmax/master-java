function check(valor) {
    for (i=0; i<valor.length; ++i)
        if (valor[i] == '@')
            return true;
    return false;
}
