import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterRoutingModule } from './register-routing.module';
import { RegisterComponent } from './register.component';
import {
	IgxIconModule,
	IgxNavbarModule,
	IgxRippleModule,
	IgxButtonModule
} from 'igniteui-angular';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NewComponent } from './new/new.component';
import { RegisteredComponent } from './registered/registered.component';
@NgModule({
    declarations: [
        RegisterComponent,
        NewComponent,
        RegisteredComponent
    ],
    imports: [
        CommonModule,
        RegisterRoutingModule,
        IgxIconModule,
        IgxNavbarModule,
        IgxRippleModule,
        IgxButtonModule
    ]
})
export class RegisterModule {
}
