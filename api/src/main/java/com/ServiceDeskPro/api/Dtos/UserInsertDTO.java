package com.ServiceDeskPro.api.Dtos;

import com.ServiceDeskPro.api.servicies.validation.UserInsertValid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@UserInsertValid
public class UserInsertDTO extends UserDTO{
    @NotBlank(message = "Campo Obrigatorio")
    @Size(min = 8,message = "deve ter no minimo 8 caracteres")
    private String password;

    public UserInsertDTO(){
        super();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
