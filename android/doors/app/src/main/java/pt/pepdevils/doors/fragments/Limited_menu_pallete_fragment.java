package pt.pepdevils.doors.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import pt.pepdevils.doors.R;
import pt.pepdevils.doors.view.PepeDialogBuilder;

import java.util.ArrayList;

/**
 * Created by pepdevils on 29/07/16.
 */

public class Limited_menu_pallete_fragment extends MenuPallete_Fragment {

    private View view;

    public static ImageButton bt_modelos, bt_colors, bt_material, bt_dimensoes,  bt_info;
    public static ImageView arrow_modelos, arrow_colors, arrow_dimensoes, arrow_info, arrow_material;

    public MenuSubModels_Fragment menuSubModels_fragment;
    public MenuSubColors_Fragment menuSubColors_fragment;
    public MenuSubMaterial_Fragment menuSubMaterial_fragment;
    public MenuSubOptions_Fragment menuSubOptions_fragment;
    public MenuSubInfo_Fragment menuSubInfo_fragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.menu_pallete_fragment, container, false);

        InicialFunctions();
        ButtonsFunctions();
        Changes();


        return view;
    }

    private void Changes() {

        bt_colors.setBackground(getResources().getDrawable(R.drawable._cores_u));
        bt_colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Warning();
            }
        });
        bt_material.setBackground(getResources().getDrawable(R.drawable._materiais_u));
        bt_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Warning();
            }
        });


    }

    private void Warning(){

        final PepeDialogBuilder dialog_builder = PepeDialogBuilder.getInstance(getActivity());

        dialog_builder
                .withTitle("Atenção")
                .withMessage("Os modelos de portas da linha\n Line Fashion\n Não são personalizáveis")
                        //.setCustomView(R.layout.dialog_alert, MainActivity.this)
                        //.setContentView(R.layout.dialog_alert)
                .withMessageCenter(true)
                .withTitleColor("#0d243d")
                .withDividerColor("#737474")
                .withMessageColor("#0d243d")
                .withDialogColor("#FFFFFF")
                .withIcon(getResources().getDrawable(R.mipmap.ic_launcher))
                .isCancelableOnTouchOutside(true)
                .withButton1Text("OK")
                .setButton1Click(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog_builder.dismiss();

                    }
                });

        dialog_builder.show();



    }



    public void InicialFunctions() {

        bt_modelos = (ImageButton) view.findViewById(R.id.bt_modelos);
        bt_colors = (ImageButton) view.findViewById(R.id.bt_colors);
        bt_material = (ImageButton) view.findViewById(R.id.bt_material);
        bt_dimensoes = (ImageButton) view.findViewById(R.id.bt_dimensoes);
        bt_info = (ImageButton) view.findViewById(R.id.bt_info);

        arrow_modelos = (ImageView) view.findViewById(R.id.arrow_modelos);
        arrow_colors = (ImageView) view.findViewById(R.id.arrow_colors);
        arrow_dimensoes = (ImageView) view.findViewById(R.id.arrow_dimensoes);
        arrow_info = (ImageView) view.findViewById(R.id.arrow_info);
        arrow_material = (ImageView) view.findViewById(R.id.arrow_material);


        // fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();


    }

    public void ButtonsFunctions() {

        //BOTÃO 1
        assert bt_modelos != null;
        bt_modelos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FecharTodosOsFragments();
                if (menuSubModels_fragment == null) {
                    menuSubModels_fragment = new MenuSubModels_Fragment();
                    replaceSubMenuFragment(menuSubModels_fragment);
                    SelecionarSetaMenu("menuSubModels_fragment", true);
                    //inflate items
                    //


                } else {
                    removeSubMenuFragment(menuSubModels_fragment);
                    menuSubModels_fragment = null;
                    SelecionarSetaMenu("menuSubModels_fragment", false);
                }
            }
        });

        //BOTÃO 2
        assert bt_colors != null;
        bt_colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FecharTodosOsFragments();
                if (menuSubColors_fragment == null) {
                    menuSubColors_fragment = new MenuSubColors_Fragment();
                    replaceSubMenuFragment(menuSubColors_fragment);
                    SelecionarSetaMenu("menuSubColors_fragment", true);
                } else {
                    removeSubMenuFragment(menuSubColors_fragment);
                    menuSubColors_fragment = null;
                    SelecionarSetaMenu("menuSubColors_fragment", false);
                }
            }
        });

        //BOTÃO 3
        assert bt_material != null;
        bt_material.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FecharTodosOsFragments();
                if (menuSubMaterial_fragment == null) {
                    menuSubMaterial_fragment = new MenuSubMaterial_Fragment();
                    replaceSubMenuFragment(menuSubMaterial_fragment);
                    SelecionarSetaMenu("menuSubMaterial_fragment", true);
                } else {
                    removeSubMenuFragment(menuSubMaterial_fragment);
                    menuSubMaterial_fragment = null;
                    SelecionarSetaMenu("menuSubMaterial_fragment", false);
                }
            }
        });

        //BOTÃO 4
        assert bt_dimensoes != null;
        bt_dimensoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FecharTodosOsFragments();
                if (menuSubOptions_fragment == null) {
                    menuSubOptions_fragment = new MenuSubOptions_Fragment();
                    replaceSubMenuFragment(menuSubOptions_fragment);
                    SelecionarSetaMenu("menuSubOptions_fragment", true);
                } else {
                    removeSubMenuFragment(menuSubOptions_fragment);
                    menuSubOptions_fragment = null;
                    SelecionarSetaMenu("menuSubOptions_fragment", false);
                }
            }
        });

        //BOTÃO 5
        assert bt_info != null;
        bt_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FecharTodosOsFragments();
                if (menuSubInfo_fragment == null) {
                    menuSubInfo_fragment = new MenuSubInfo_Fragment();
                    replaceSubMenuFragment(menuSubInfo_fragment);
                    SelecionarSetaMenu("menuSubInfo_fragment", true);
                } else {
                    removeSubMenuFragment(menuSubInfo_fragment);
                    menuSubInfo_fragment = null;
                    SelecionarSetaMenu("menuSubInfo_fragment", false);
                }
            }
        });


    }

    private void replaceSubMenuFragment(Fragment fragment) {




        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container_sub, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .commit();

    }

    private void removeSubMenuFragment(Fragment fragment) {

        getActivity().getSupportFragmentManager().beginTransaction()
                .remove(fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commit();


    }

    public static void SelecionarSetaMenu(String tag_fragment, boolean opening) {

        FecharTodasAsSetasMenu();

        if (opening) {

            switch (tag_fragment) {
                case "menuSubModels_fragment":
                    arrow_modelos.setVisibility(View.VISIBLE);
                    break;
                case "menuSubColors_fragment":
                    arrow_colors.setVisibility(View.VISIBLE);
                    break;
                case "menuSubMaterial_fragment":
                    arrow_material.setVisibility(View.VISIBLE);
                    break;
                case "menuSubOptions_fragment":
                    arrow_dimensoes.setVisibility(View.VISIBLE);
                    break;
                case "menuSubInfo_fragment":
                    arrow_info.setVisibility(View.VISIBLE);
                    break;
                default:
                    arrow_modelos.setVisibility(View.GONE);
                    arrow_colors.setVisibility(View.GONE);
                    arrow_material.setVisibility(View.GONE);
                    arrow_dimensoes.setVisibility(View.GONE);
                    arrow_info.setVisibility(View.GONE);
                    break;
            }

        } else {

            switch (tag_fragment) {
                case "menuSubModels_fragment":
                    arrow_modelos.setVisibility(View.INVISIBLE);
                    break;
                case "menuSubColors_fragment":
                    arrow_colors.setVisibility(View.INVISIBLE);
                    break;
                case "menuSubMaterial_fragment":
                    arrow_material.setVisibility(View.INVISIBLE);
                    break;
                case "menuSubOptions_fragment":
                    arrow_dimensoes.setVisibility(View.INVISIBLE);
                    break;
                case "menuSubInfo_fragment":
                    arrow_info.setVisibility(View.INVISIBLE);
                    break;
                default:
                    arrow_modelos.setVisibility(View.GONE);
                    arrow_colors.setVisibility(View.GONE);
                    arrow_material.setVisibility(View.GONE);
                    arrow_dimensoes.setVisibility(View.GONE);
                    arrow_info.setVisibility(View.GONE);
                    break;
            }

        }
    }

    private static void FecharTodasAsSetasMenu() {

        arrow_modelos.setVisibility(View.INVISIBLE);
        arrow_colors.setVisibility(View.INVISIBLE);
        arrow_material.setVisibility(View.INVISIBLE);
        arrow_dimensoes.setVisibility(View.INVISIBLE);
        arrow_info.setVisibility(View.INVISIBLE);

    }

    private void FecharTodosOsFragments() {

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(menuSubColors_fragment);
        fragmentArrayList.add(menuSubInfo_fragment);
        fragmentArrayList.add(menuSubMaterial_fragment);
        fragmentArrayList.add(menuSubModels_fragment);
        fragmentArrayList.add(menuSubOptions_fragment);

        for (int i = 0; i < fragmentArrayList.size(); i++) {

            if (fragmentArrayList.get(i) != null) {
                removeSubMenuFragment(fragmentArrayList.get(i));
            }
        }


    }
}