import { BrowserModule, HammerModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './register/register.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatStepperModule } from '@angular/material/stepper';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { WebcamModule } from 'ngx-webcam';
import {
	IgxInputGroupModule,
	IgxIconModule,
	IgxNavbarModule,
	IgxRippleModule,
	IgxButtonModule,
	IgxCheckboxModule,
	IgxRadioModule,
	IgxButtonGroupModule,
	IgxToastModule
} from 'igniteui-angular';
@NgModule({
    declarations: [
        AppComponent,
        RegisterComponent
    ],
    imports: [
        WebcamModule,
        FormsModule,
        ReactiveFormsModule,
        MatStepperModule,
        MatFormFieldModule,
        MatInputModule,
        BrowserModule,
        HammerModule,
        AppRoutingModule,
        BrowserAnimationsModule,
        HttpClientModule,
        IgxInputGroupModule,
        IgxIconModule,
        IgxNavbarModule,
        IgxRippleModule,
        IgxButtonModule,
        IgxCheckboxModule,
        IgxRadioModule,
        IgxButtonGroupModule,
        IgxToastModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
