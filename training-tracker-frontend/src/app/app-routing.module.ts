import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {HomeComponent} from "./home/component/home/home.component";
import {PageNotFoundComponent} from "./not-found/component/page-not-found/page-not-found.component";

const routes: Routes = [
  {path: '', pathMatch: 'full', component: HomeComponent},
  {path: 'auth', loadChildren: './auth/auth.module#AuthModule'},
  {path: '**', redirectTo: '/not-found'},
  {path: 'not-found', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
