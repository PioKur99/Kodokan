import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RegisterRoutingModule } from './register-routing.module';
import { RegisterComponent } from './register.component';
import {
	IgxIconModule,
	IgxNavbarModule,
	IgxRippleModule,
	IgxButtonModule,
	IgxNavigationDrawerModule,
	IgxLayoutModule,
	IgxToggleModule,
	IgxInputGroupModule,
    IgxTooltipModule
} from 'igniteui-angular';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NewComponent } from './new/new.component';
import { RegisteredComponent } from './registered/registered.component';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { LottieModule } from 'ngx-lottie';
import player from 'lottie-web';

import { HttpClientModule } from '@angular/common/http';

export function playerFactory() {
  return player;
}

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
        IgxButtonModule,
        IgxNavigationDrawerModule,
        IgxLayoutModule,
        IgxToggleModule,
        FormsModule,
        IgxInputGroupModule,
        IgxTooltipModule,
        LottieModule.forRoot({ player: playerFactory }),
        HttpClientModule
    ]
})
export class RegisterModule {
}
