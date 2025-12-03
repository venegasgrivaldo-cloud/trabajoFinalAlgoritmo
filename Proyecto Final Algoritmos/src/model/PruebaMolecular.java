package model;

public class PruebaMolecular {
    private String uuid;
    private String fechaMuestra;
    private String institucion;
    private String ubigeo;
    private int edad;
    private String sexo;
    private String departamento;
    private String provincia;
    private String distrito;
    private String tipoMuestra;
    private String resultado;

    public PruebaMolecular(String uuid, String fechaMuestra, String institucion, String ubigeo,
                           int edad, String sexo, String departamento, String provincia,
                           String distrito, String tipoMuestra, String resultado) {
        this.uuid = uuid;
        this.fechaMuestra = fechaMuestra;
        this.institucion = institucion;
        this.ubigeo = ubigeo;
        this.edad = edad;
        this.sexo = sexo;
        this.departamento = departamento;
        this.provincia = provincia;
        this.distrito = distrito;
        this.tipoMuestra = tipoMuestra;
        this.resultado = resultado;
    }

    public String getUuid() { return uuid; }
    public String getFechaMuestra() { return fechaMuestra; }
    public String getInstitucion() { return institucion; }
    public String getUbigeo() { return ubigeo; }
    public int getEdad() { return edad; }
    public String getSexo() { return sexo; }
    public String getDepartamento() { return departamento; }
    public String getProvincia() { return provincia; }
    public String getDistrito() { return distrito; }
    public String getTipoMuestra() { return tipoMuestra; }
    public String getResultado() { return resultado; }
}
