import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterUserRoutingModule } from './register-user-routing.module';
import { RegisterUserComponent } from './register-user.component';
import { IgxIconModule, IgxNavbarModule } from 'igniteui-angular';
@NgModule({
    declarations: [
        RegisterUserComponent
    ],
    imports: [
        CommonModule,
        RegisterUserRoutingModule,
        IgxIconModule,
        IgxNavbarModule
    ]
})
export class RegisterUserModule {
}
