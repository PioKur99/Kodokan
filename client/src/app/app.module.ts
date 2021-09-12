import { BrowserModule, HammerModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { RegisterComponent } from './register/register.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatStepperModule } from '@angular/material/stepper';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { WebcamModule } from 'ngx-webcam';
import { ImageCropperModule } from 'ngx-image-cropper';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {
	IgxInputGroupModule,
	IgxIconModule,
	IgxNavbarModule,
	IgxRippleModule,
	IgxButtonModule,
	IgxCheckboxModule,
	IgxRadioModule,
	IgxButtonGroupModule,
	IgxToastModule,
	IgxTooltipModule,
	IgxDialogModule
} from 'igniteui-angular';

@NgModule({
    declarations: [
        AppComponent,
        RegisterComponent,
    ],
    imports: [
        ImageCropperModule,
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
        IgxTooltipModule,
        IgxInputGroupModule,
        IgxIconModule,
        IgxNavbarModule,
        IgxRippleModule,
        IgxButtonModule,
        IgxCheckboxModule,
        IgxRadioModule,
        IgxButtonGroupModule,
        IgxToastModule,
        IgxDialogModule
    ],
    providers: [],
    bootstrap: [AppComponent]
})
export class AppModule {
}
