import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomerPanelRoutingModule } from './customer-panel-routing.module';
import { CustomerPanelComponent } from './customer-panel.component';
import {
    IgxNavigationDrawerModule,
    IgxRippleModule,
    IgxButtonModule,
    IgxIconModule,
    IgxBottomNavModule
} from 'igniteui-angular';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MyDataComponent } from './my-data/my-data.component';
import { MyFamilyComponent } from './my-family/my-family.component';
import { MyTrainingsComponent } from './my-trainings/my-trainings.component';
import { MyMembershipsComponent } from './my-memberships/my-memberships.component';
import { NewMembershipsComponent } from './new-memberships/new-memberships.component';
import { LottieModule } from 'ngx-lottie';
import player from 'lottie-web';
import { MainPageComponent } from './main-page/main-page.component';

export function playerFactory() {
    return player
}

@NgModule({
    declarations: [
        CustomerPanelComponent,
        MyDataComponent,
        MyFamilyComponent,
        MyTrainingsComponent,
        MyMembershipsComponent,
        NewMembershipsComponent,
        MainPageComponent
    ],
    imports: [
        CommonModule,
        CustomerPanelRoutingModule,
        IgxNavigationDrawerModule,
        IgxRippleModule,
        IgxButtonModule,
        IgxIconModule,
        IgxBottomNavModule,
        LottieModule.forRoot({ player: playerFactory })
    ]
})
export class CustomerPanelModule {
}
